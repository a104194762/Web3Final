import { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Logs() {
    const [logs, setLogs] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get('/api/logs')
            .then(res => {
                setLogs(res.data);
            })
            .catch(err => {
                console.error("Failed to fetch logs", err);
                alert("Failed to load logs");
            });
    }, []);

    return (
        <div style={{ padding: '20px' }}>
            <button
                onClick={() => navigate(-1)}
                style={{ marginBottom: '20px', padding: '8px 16px', cursor: 'pointer' }}
            >
                ← Back
            </button>

            <h2>System Logs</h2>
            <ul>
                {logs.map((log) => (
                    <li key={log.id} style={{ marginBottom: '8px' }}>
                        <strong>{log.timestamp}</strong> -
                        <em> {log.username}</em>: {log.action}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default Logs;