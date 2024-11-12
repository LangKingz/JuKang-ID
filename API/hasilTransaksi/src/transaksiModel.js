// simulasi memori data
let transaksiData = [];

// export fungsi es modul
export const getTransaksiById = (id) => transaksiData.find(item => item.id === id);
export const setTransaksiData = (data) => { transaksiData = data; };
