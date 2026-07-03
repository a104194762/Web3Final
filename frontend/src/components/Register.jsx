import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Register() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleRegister = async () => {
        try {
            // 对应 UserController 的 /api/users/register 接口[cite: 11]
            await axios.post('/api/users/register', { username, password });
            alert("Registration successful!");
            navigate('/'); // 成功后跳转回登录页
        } catch (err) {
            alert("Registration failed: " + (err.response?.data || "Unknown error"));
        }
    };

    return (
        <div className="container">
            <h2>Create Account</h2>
            <input placeholder="Username" onChange={(e) => setUsername(e.target.value)} />
            <input type="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)} />
            <button onClick={handleRegister}>Register</button>
            <a href="/" className="link">Already have an account? Login</a>
        </div>
    );
}

export default Register;