import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

axios.defaults.withCredentials = true;

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            await axios.post('/api/auth/login', { username, password });
            navigate('/dashboard');
        } catch (err) {
            console.error(err);
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