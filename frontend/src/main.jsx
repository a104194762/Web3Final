import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './index.css';
import App from './App.jsx';
import Login from './components/Login.jsx';
import Register from './components/Register.jsx';
import Logs from './components/Logs.jsx';

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/dashboard" element={<App />} />
                <Route path="/logs" element={<Logs />} />
            </Routes>
        </BrowserRouter>
    </StrictMode>
);