// Base de datos SQLite de NotasApp
const Database = require("better-sqlite3");
const bcrypt = require("bcryptjs");
const path = require("path");

// el archivo notas.db se crea en la carpeta del backend
const db = new Database(path.join(__dirname, "notas.db"));

// tabla de usuarios
db.exec(`
    CREATE TABLE IF NOT EXISTS usuarios (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nombre TEXT NOT NULL,
        email TEXT NOT NULL UNIQUE,
        password TEXT NOT NULL
    )
`);

// tabla de notas, cada nota tiene el id del usuario que la creo
db.exec(`
    CREATE TABLE IF NOT EXISTS notas (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        usuario_id INTEGER NOT NULL,
        titulo TEXT NOT NULL,
        contenido TEXT NOT NULL,
        fecha_creacion TEXT NOT NULL DEFAULT (datetime('now', 'localtime')),
        FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
    )
`);

// si no hay usuarios se crea uno para poder entrar a la app
const total = db.prepare("SELECT COUNT(*) AS total FROM usuarios").get().total;
if (total === 0) {
    const password = bcrypt.hashSync("654321", 10);
    db.prepare("INSERT INTO usuarios (nombre, email, password) VALUES (?, ?, ?)")
        .run("Luis Quan", "luis@testmail.com", password);
    console.log("Usuario creado: luis@testmail.com / 654321");
}

module.exports = db;
