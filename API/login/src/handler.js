import { nanoid } from 'nanoid';
import users from './users.js';

const registerHandler = (request, h) => {
    const { namalengkap, nomortelp, email, password } = request.payload;

    if (!namalengkap || !nomortelp || !email || !password) {
        return h.response({
            status: 'fail',
            message: 'Gagal menambahkan user. Lengkapi data!',
        }).code(400);
    }

    const id_user = nanoid(10);
    const newUser = { id_user, namalengkap, nomortelp, email, password };
    users.push(newUser);

    return h.response({
        status: 'success',
        message: 'User berhasil ditambahkan',
        data: {
            user: newUser,
        },
    }).code(201);
};

const loginHandler = (request, h) => {
    const { email, password } = request.payload;

    if (!email || !password) {
        return h.response({
            status: 'fail',
            message: 'Gagal login. Lengkapi data!',
        }).code(400);
    }

    const user = users.find((u) => u.email === email && u.password === password);

    if (!user) {
        return h.response({
            status: 'fail',
            message: 'Email atau password salah',
        }).code(401);
    }

    return h.response({
        status: 'success',
        message: 'Login berhasil',
        data: {
            user,
        },
    }).code(200);
};

export { loginHandler, registerHandler };

