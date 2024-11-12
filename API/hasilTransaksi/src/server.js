import Hapi from '@hapi/hapi';
import { getTransaksiById } from './transaksiModel.js';

const init = async () => {
  const server = Hapi.server({
    port: 3000,
    host: 'localhost',
  });

  server.route({
    method: 'GET',
    path: '/transaksi/{id}',
    handler: (request, h) => {
      const { id } = request.params;
      const transaksi = getTransaksiById(id);

      if (!transaksi) {
        return h.response({
          status: 'fail',
          message: 'Transaksi tidak ditemukan',
        }).code(404);
      }

      return h.response({
        status: 'Sukses',
        data: transaksi,
      }).code(200);
    },
  });

  await server.start();
  console.log('Server hasilTransaksi berjalan di %s', server.info.uri);
};

process.on('unhandledRejection', (err) => {
  console.log(err);
  process.exit(1);
});

init();
