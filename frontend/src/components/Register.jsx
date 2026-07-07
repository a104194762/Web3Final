import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Register() {
    // state for new credentials input
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    // navigation router hook
    const navigate = useNavigate();

    // handle user account creation submission
    const handleRegister = async () => {
        try {
            // send new profile registration details to backend
            await axios.post('/api/users/register', { username, password });
            alert("Registration successful!");
            // redirect to entry login view
            navigate('/');
        } catch (err) {
            // alert client-side with backend context fallback
            alert("Registration failed: " + (err.response?.data || "Unknown error"));
        }
    };

    return (
        <div className="container">
            <h2>Create Account</h2>
            {/* username entry tracking field */}
            <input placeholder="Username" onChange={(e) => setUsername(e.target.value)} />
            {/* secure password mask entry field */}
            <input type="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)} />
            {/* triggering execution submission button */}
            <button onClick={handleRegister}>Register</button>
            {/* fallback landing route link redirect */}
            <a href="/" className="link">Already have an account? Login</a>
        </div>
    );
}

export default Register;