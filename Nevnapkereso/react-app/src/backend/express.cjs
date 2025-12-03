const express = require('express');
const cors = require('cors');
const mysql = require('mysql2');
const app = express();
const port = 3333;

app.use(express.json());
app.use(cors());

const conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'nevnapok'
});

const honapok = [
    {id: 1, honap: 'január'},
    {id: 2, honap: 'február'},
    {id: 3, honap: 'március'},
    {id: 4, honap: 'április'},
    {id: 5, honap: 'május'},
    {id: 6, honap: 'június'},
    {id: 7, honap: 'július'},
    {id: 8, honap: 'augusztus'},
    {id: 9, honap: 'szeptember'},
    {id: 10, honap: 'október'},
    {id: 11, honap: 'november'},
    {id: 12, honap: 'december'}
];

conn.connect(err => {
    if (err) console.warn;
    else console.log('Succesfully connected to database: nevnapok.');
});

app.get('/api/nevnapok', (req, res) => {
    const nev = req.query?.nev;
    const nap = req.query?.nap;

    if (!nev && !nap) {
        conn.query( `SELECT * FROM nevnap`, async (err, result, fields) => {
            if (err) return res.status(500).json({hiba: 'nincs találat'});
            if (result) {
                const data = result;
                const responseData = [];
                data.map((element) => {
                    const honapNeve = honapok.find( (honap) => honap.id === element.ho)?.honap;
                    responseData.push({
                        datum: `${honapNeve} ${element.nap}.`,
                        nevnap1: element.nev1,
                        nevnap2: element.nev2
                    });
                });
                return res.status(200).json(responseData);
            }
            else return res.status(404).json({hiba: 'nincs találat'});
        });
    }

    if (nev) {
        conn.query(`
            SELECT * FROM nevnap WHERE nev1 = ? OR nev2 = ?`,
            [nev, nev],
            (err, result, fields) => {
                if (err) return res.status(404).json({hiba: 'nincs találat'});
                if (result) {
                    const data = result;

                    if (data.length > 0) {
                        const responseData = [];
                        data.map((element) => {
                            const honapNeve = honapok.find( (honap) => honap.id === element.ho)?.honap;
                            responseData.push({
                                datum: `${honapNeve} ${element.nap}.`,
                                nevnap1: element.nev1,
                                nevnap2: element.nev2
                            });
                        });
                        return res.status(200).json(responseData);
                    } else return res.status(404).json({hiba: 'nincs találat'});
                }
                else return res.status(404).json({hiba: 'nincs találat'});
            }
        );
    }

    if (nap) {
        const datumParts = nap.split('-');
        
        if (datumParts.length !== 2 || !datumParts[0] || !datumParts[1] || datumParts[0] < 1 || datumParts[0] > 12 || datumParts[1] < 1 || datumParts[1] > 31) return res.status(400).json({minta1: "/?nap=12-31", minta2:"/?nev=Szilveszter"});
        else {
            const datum = datumParts.map(Number);
            conn.query(`
                SELECT * FROM nevnap WHERE ho = ? AND nap = ?`,
                [datum[0], datum[1]],
                (err, result, fields) => {
                    if (err) return res.status(404).json({hiba: 'nincs találat'});
                    if (result) {
                        const data = result;

                        if (data.length > 0) {
                            const responseData = [];
                            data.map((element) => {
                                const honapNeve = honapok.find( (honap) => honap.id === element.ho)?.honap;
                                responseData.push({
                                    datum: `${honapNeve} ${element.nap}.`,
                                    nevnap1: element.nev1,
                                    nevnap2: element.nev2
                                });
                            });
                            return res.status(200).json(responseData);
                        } else return res.status(404).json({hiba: 'nincs találat'});
                    }
                    else return res.status(404).json({hiba: 'nincs találat'});
                }
            );
        }
    }
});

app.listen(port, () => {
    console.log('Express backend server is running on port:', port);
})