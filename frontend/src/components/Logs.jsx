import { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Logs() {
    // state for holding logged records
    const [logs, setLogs] = useState([]);
    // navigation router hook
    const navigate = useNavigate();

    // fetch dataset once upon component mount
    useEffect(() => {
        // get request targeting administration metrics
        axios.get('/api/logs')
            .then(res => {
                // update local records array
                setLogs(res.data);
            })
            .catch(err => {
                // log runtime tracing and notify user
                console.error("Failed to fetch logs", err);
                alert("Failed to load logs");
            });
    }, []);

    return (
        <div style={{ padding: '20px' }}>
            {/* back-tracking routing controller button */}
            <button
                onClick={() => navigate(-1)}
                style={{ marginBottom: '20px', padding: '8px 16px', cursor: 'pointer' }}
            >
                ← Back
            </button>

            <h2>System Logs</h2>
            {/* dynamically rendered list entries wrapper */}
            <ul>
                {logs.map((log) => (
                    // target entity list iteration marker key
                    <li key={log.id} style={{ marginBottom: '8px' }}>
                        {/* localized creation date field */}
                        <strong>{log.timestamp}</strong> -
                        {/* acting identity credential name */}
                        <em> {log.username}</em>: {log.action}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default Logs;