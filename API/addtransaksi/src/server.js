import Hapi from '@hapi/hapi';
import { addTransaksi, getAllTransaksi, getTransaksiById } from './transaksiModel.js';

const init = async () => {
  const server = Hapi.server({
    port: 3000,
    host: 'localhost',
  });

  // posttransaksi
  server.route({
    method: 'POST',
    path: '/addtransaksi',
    handler: (request, h) => {
      const transaksi = {
        id: new Date().getTime().toString(), // Simulate a unique ID
        ...request.payload,
        createdAt: new Date().toISOString(),
      };
      addTransaksi(transaksi);
      return h.response({
        status: 'success',
        data: transaksi,
      }).code(201);
    },
  });

  // get semua transaksi
  server.route({
    method: 'GET',
    path: '/transaksi',
    handler: (request, h) => {
      return h.response({
        status: 'success',
        data: getAllTransaksi(),
      }).code(200);
    },
  });

  // Rget berdasarkan id
  server.route({
    method: 'GET',
    path: '/transaksi/{id}',
    handler: (request, h) => {
      const { id } = request.params;
      const transaksi = getTransaksiById(id);

      if (!transaksi) {
        return h.response({
          status: 'fail',
          message: 'Transaksi not found',
        }).code(404);
      }

      return h.response({
        status: 'success',
        data: transaksi,
      }).code(200);
    },
  });

  await server.start();
  console.log('Server running on %s', server.info.uri);
};

process.on('unhandledRejection', (err) => {
  console.log(err);
  process.exit(1);
});

init();
