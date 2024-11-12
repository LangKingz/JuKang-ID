import { loginHandler, registerHandler } from './handler.js';

const routes = [
    {
        method: 'POST',
        path: '/users/register',
        handler: registerHandler,
    },
    {
        method: 'POST',
        path: '/users/login',
        handler: loginHandler,
    },
];

export default routes;
