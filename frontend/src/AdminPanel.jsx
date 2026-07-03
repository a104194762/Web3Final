import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AdminDashboard = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        const res = await axios.get('/api/admin/users'); // 你的后端管理接口
        setUsers(res.data);
    };

    const deleteUser = async (id) => {
        await axios.delete(`/api/admin/users/${id}`);
        fetchUsers();
    };

    return (
        <div>
            <h1>Admin User Management</h1>
            {users.map(u => (
                <div key={u.id}>
                    {u.username}
                    <button onClick={() => deleteUser(u.id)}>Delete</button>
                </div>
            ))}
        </div>
    );
};
export default AdminDashboard;