-- Query untuk pembuatan tabel transaksi dalam file database.db

CREATE TABLE transaksi (
    no_faktur INTEGER PRIMARY KEY AUTOINCREMENT,
    kode_barang TEXT, 
    nama_barang TEXT, 
    harga_barang INTEGER NOT NULL, 
    jumlah_beli INTEGER NOT NULL, 
    total INTEGER NOT NULL
);