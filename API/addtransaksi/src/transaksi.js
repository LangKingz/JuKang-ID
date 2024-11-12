import { nanoid } from 'nanoid';

const transaksi = [];

export const addTransaksiHandler = (request, h) => {
  const {
    nama,
    keahlian,
    deskripsi,
    tanggal,
    alamat,
    metodePembayaran,
    total,
  } = request.payload;

  const id = nanoid();
  const newTransaksi = {
    id,
    nama,
    keahlian,
    deskripsi,
    tanggal,
    alamat,
    metodePembayaran,
    total,
    createdAt: new Date().toISOString(),
  };

  transaksi.push(newTransaksi);

  return h.response({
    status: 'Sukses',
    message: 'Transaksi berhasil ditambahkan',
    data: {
      transaksiId: id,
    },
  }).code(201);
};
