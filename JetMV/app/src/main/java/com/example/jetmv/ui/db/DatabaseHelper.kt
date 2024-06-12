package com.example.jetmv.ui.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Crear las tablas y pre-cargar los datos
        db.execSQL(CREATE_USUARIOS_TABLE)
        db.execSQL(CREATE_ENTRENAMIENTOS_TABLE)
        db.execSQL(CREATE_RECORDS_PERSONALES_TABLE)
        db.execSQL(CREATE_DISTRIBUCION_PESOS_TABLE)
        preCargaDatos(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Manejar actualizaciones de la base de datos si es necesario
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        db.execSQL("DROP TABLE IF EXISTS entrenamientos")
        db.execSQL("DROP TABLE IF EXISTS records_personales")
        db.execSQL("DROP TABLE IF EXISTS distribucion_pesos")
        onCreate(db)
    }

    private fun preCargaDatos(db: SQLiteDatabase) {
        db.execSQL("INSERT INTO usuarios (nombre_usuario, password) VALUES ('user1@example.com', 'P@ssw0rd1')")
        db.execSQL("INSERT INTO usuarios (nombre_usuario, password) VALUES ('user2@example.com', 'S3cureP@ss')")
        db.execSQL("INSERT INTO usuarios (nombre_usuario, password) VALUES ('user3@example.com', 'Str0ngP@ss!')")
        db.execSQL("INSERT INTO usuarios (nombre_usuario, password) VALUES ('user4@example.com', 'Adm1n@tr@t0r')")
        db.execSQL("INSERT INTO usuarios (nombre_usuario, password) VALUES ('user5@example.com', 'M@nager123')")
        db.execSQL("INSERT INTO usuarios (nombre_usuario, password) VALUES ('user6@example.com', 'Qw3rty!P@ss')")
        db.execSQL("INSERT INTO usuarios (nombre_usuario, password) VALUES ('user7@example.com', 'V3ryS3cur3!')")
        db.execSQL("INSERT INTO usuarios (nombre_usuario, password) VALUES ('user8@example.com', 'An0th3rP@ss')")
        db.execSQL("INSERT INTO usuarios (nombre_usuario, password) VALUES ('user9@example.com', 'P@ssw0rdStr0ng')")
        db.execSQL("INSERT INTO usuarios (nombre_usuario, password) VALUES ('user10@example.com', 'S@feP@ss123')")
        db.execSQL("INSERT INTO entrenamientos (usuario_id, fecha, ejercicio) VALUES (1, '2024-06-01', 'Bench Press')")
        db.execSQL("INSERT INTO entrenamientos (usuario_id, fecha, ejercicio) VALUES (1, '2024-06-01', 'Deadlift')")
        db.execSQL("INSERT INTO entrenamientos (usuario_id, fecha, ejercicio) VALUES (2, '2024-06-01', 'Snatch')")
        db.execSQL("INSERT INTO records_personales (usuario_id, bench_press, shoulder_press, snatch, clean, deadlift) VALUES (1, 100, 80, 60, 90, 120)")
        db.execSQL("INSERT INTO records_personales (usuario_id, bench_press, shoulder_press, snatch, clean, deadlift) VALUES (2, 110, 85, 65, 95, 130)")
        db.execSQL("INSERT INTO distribucion_pesos (usuario_id, peso_total, discos_45, discos_35, discos_5, discos_2_5) VALUES (1, 220, 2, 2, 2, 2)")
        db.execSQL("INSERT INTO distribucion_pesos (usuario_id, peso_total, discos_45, discos_25, discos_15, discos_10) VALUES (2, 195, 2, 2, 2, 1)")
    }

    fun checkUserCredentials(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM usuarios WHERE nombre_usuario = ? AND password = ?", arrayOf(username, password))
        val isValid = cursor.count > 0
        cursor.close()
        db.close()
        return isValid
    }

    companion object {
        private const val DATABASE_NAME = "gimnasio.db"
        private const val DATABASE_VERSION = 3

        private const val CREATE_USUARIOS_TABLE = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre_usuario TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL
            );
        """

        private const val CREATE_ENTRENAMIENTOS_TABLE = """
            CREATE TABLE IF NOT EXISTS entrenamientos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario_id INTEGER,
                fecha DATE NOT NULL,
                ejercicio TEXT NOT NULL,
                FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
            );
        """

        private const val CREATE_RECORDS_PERSONALES_TABLE = """
            CREATE TABLE IF NOT EXISTS records_personales (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario_id INTEGER,
                bench_press REAL,
                shoulder_press REAL,
                snatch REAL,
                clean REAL,
                deadlift REAL,
                FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
            );
        """

        private const val CREATE_DISTRIBUCION_PESOS_TABLE = """
            CREATE TABLE IF NOT EXISTS distribucion_pesos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario_id INTEGER,
                peso_total REAL NOT NULL,
                barra REAL NOT NULL DEFAULT 45,
                discos_45 INTEGER DEFAULT 0,
                discos_35 INTEGER DEFAULT 0,
                discos_25 INTEGER DEFAULT 0,
                discos_15 INTEGER DEFAULT 0,
                discos_10 INTEGER DEFAULT 0,
                discos_5 INTEGER DEFAULT 0,
                discos_2_5 INTEGER DEFAULT 0,
                FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
            );
        """
    }
}