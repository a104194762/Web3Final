import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

// enable global session cookies
axios.defaults.withCredentials = true;

function Login() {
    // state for credentials input
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    // navigation router hook
    const navigate = useNavigate();

    // handle authentication submission
    const handleLogin = async () => {
        try {
            // send credentials payload to backend
            await axios.post('/api/auth/login', { username, password });
            // redirect to main dashboard view
            navigate('/dashboard');
        } catch (err) {
            // log error details and alert
            console.error(err);
            alert("Login failed! Please check your credentials.");
        }
    };

    return (
        <div className="container">
            <h2>Login</h2>
            {/* username controlled input element */}
            <input placeholder="Username" onChange={(e) => setUsername(e.target.value)} />
            {/* password mask input element */}
            <input type="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)} />
            {/* triggering submit interaction button */}
            <button onClick={handleLogin}>Login</button>
            {/* fallback registration redirect link */}
            <a href="/register" className="link">Register new account</a>
        </div>
    );
}

export default Login;