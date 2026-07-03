import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            // 对应 AuthController 的 /api/auth/login 接口[cite: 7, 8]
            await axios.post('/api/auth/login', { username, password });
            navigate('/dashboard'); // 登录成功跳转至组队页 (App.jsx)
        } catch (err) {
            alert("Login failed! Please check your credentials.");
        }
    };

    return (
        <div className="container">
            <h2>Login</h2>
            <input placeholder="Username" onChange={(e) => setUsername(e.target.value)} />
            <input type="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)} />
            <button onClick={handleLogin}>Login</button>
            <a href="/register" className="link">Register new account</a>
        </div>
    );
}
const handleLogout = async (e) => {
    e.preventDefault();
    try {
        await axios.post('/api/auth/logout');
        alert("Logged out successfully");
        window.location.href = '/';
    } catch (err) {
        console.error("Logout error:", err);
        alert("Logout failed");
    }
};
export default Login;