let transaksiData = [];

export const getAllTransaksi = () => transaksiData;
export const getTransaksiById = (id) => transaksiData.find(item => item.id === id);
export const addTransaksi = (transaksi) => {
  transaksiData.push(transaksi);
  return transaksi;
};
