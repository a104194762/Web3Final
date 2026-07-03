import { useState, useEffect } from 'react';
import axios from 'axios';
import './index.css';

axios.defaults.withCredentials = true;

const POKEMON_TYPES = [
    "normal", "fire", "water", "grass", "electric", "ice",
    "bug", "flying", "ground", "rock", "fighting", "psychic",
    "ghost", "poison", "dark", "steel", "dragon", "fairy"
];

function App() {
    const [allTeams, setAllTeams] = useState(Array(3).fill(null).map(() => Array(6).fill(null)));
    const [currentTeamIndex, setCurrentTeamIndex] = useState(0);
    const [selectedSlotIndex, setSelectedSlotIndex] = useState(-1);
    const [teamNames, setTeamNames] = useState(["", "", ""]);
    const [query, setQuery] = useState("");
    const [searchResults, setSearchResults] = useState([]);
    const [filterType, setFilterType] = useState("");
    const [selectedResultId, setSelectedResultId] = useState(null); // 选中宝可梦的 ID

    useEffect(() => {
        fetchTeams();
    }, []);

    useEffect(() => {
        const url = filterType ? `/api/pokemons?type=${filterType}` : '/api/pokemons';
        axios.get(url).then(res => {
            setSearchResults(res.data);
            setSelectedResultId(null); // 切换筛选时清空选中
        });
    }, [filterType]);

    const fetchTeams = () => {
        axios.get('/api/teams')
            .then(res => {
                const newTeams = Array(3).fill(null).map(() => Array(6).fill(null));
                const newNames = ["", "", ""];
                res.data.forEach(t => {
                    if (t.teamIndex < 3) {
                        newNames[t.teamIndex] = t.teamName || "";
                        t.members.forEach((m, i) => { if (i < 6) newTeams[t.teamIndex][i] = m.pokemon; });
                    }
                });
                setAllTeams(newTeams);
                setTeamNames(newNames);
            })
            .catch(err => {
                if (err.response?.status === 401) {
                    alert("Session expired, please login again.");
                    window.location.href = '/';
                }
            });
    };

    const handleSearch = async () => {
        const res = await axios.get(`/api/pokemons/search?query=${encodeURIComponent(query)}`);
        setSearchResults(res.data);
        setSelectedResultId(null);
    };

    const addPokemon = (pokemon) => {
        // 1. 检查是否选择了槽位
        if (selectedSlotIndex === -1) return alert("Please select a slot first!");

        // 2. 检查队伍中是否已经存在该宝可梦 (通过 ID 判断)
        const isAlreadyInTeam = allTeams[currentTeamIndex].some(
            member => member && member.id === pokemon.id
        );

        if (isAlreadyInTeam) {
            return alert("This Pokémon is already in your team!");
        }

        // 3. 执行添加
        const nextTeams = [...allTeams];
        nextTeams[currentTeamIndex][selectedSlotIndex] = pokemon;
        setAllTeams(nextTeams);
        setSelectedSlotIndex(-1);
        setSelectedResultId(null); // 添加成功后取消搜索列表的高亮
    };

    const removePokemon = (index) => {
        if (!window.confirm("Are you sure you want to remove this Pokémon?")) return;
        const nextTeams = [...allTeams];
        nextTeams[currentTeamIndex][index] = null;
        setAllTeams(nextTeams);
    };

    const saveTeam = async () => {
        const payload = {
            teamIndex: currentTeamIndex,
            teamName: teamNames[currentTeamIndex],
            members: allTeams[currentTeamIndex].filter(m => m).map(m => ({
                id: m.id,
                nameEn: m.nameEn,
                imageUrl: m.imageUrl
            }))
        };
        try {
            await axios.post('/api/teams/save', payload);
            alert("Saved successfully");
        } catch (err) {
            alert("Save failed");
        }
    };

    const handleLogout = async () => {
        try {
            await axios.post('/api/auth/logout');
            window.location.href = '/';
        } catch (err) {
            alert("Logout failed");
        }
    };

    return (
        <div className="App">
            <h2>Current Team: {currentTeamIndex + 1}</h2>
            <input
                value={teamNames[currentTeamIndex]}
                onChange={(e) => {
                    const nextNames = [...teamNames];
                    nextNames[currentTeamIndex] = e.target.value;
                    setTeamNames(nextNames);
                }}
                placeholder="Team Name"
            />

            <div className="team-container">
                {allTeams[currentTeamIndex].map((p, i) => (
                    <div key={i} className={`slot ${selectedSlotIndex === i ? 'selected' : ''}`} onClick={() => setSelectedSlotIndex(i)}>
                        {p ? (
                            <>
                                <img src={p.imageUrl} width="80" alt={p.nameEn} />
                                <button onClick={(e) => { e.stopPropagation(); removePokemon(i); }}>Delete</button>
                            </>
                        ) : "Empty"}
                    </div>
                ))}
            </div>

            <button onClick={() => setCurrentTeamIndex((currentTeamIndex + 1) % 3)}>Switch Team</button>
            <button onClick={saveTeam}>Save Team</button>
            <button onClick={handleLogout} style={{ backgroundColor: '#ff7675', marginTop: '20px' }}>Logout</button>

            <hr />

            <h3>Search & Filter</h3>
            <input value={query} onChange={(e) => setQuery(e.target.value)} placeholder="Search Pokemon" />
            <button onClick={handleSearch}>Search</button>

            <select value={filterType} onChange={(e) => setFilterType(e.target.value)}>
                <option value="">All Types</option>
                {POKEMON_TYPES.map(type => (
                    <option key={type} value={type}>{type.toUpperCase()}</option>
                ))}
            </select>

            <div id="searchResult">
                {searchResults.map(p => (
                    <div
                        key={p.id}
                        className={`pokemon-item ${selectedResultId === p.id ? 'selected' : ''}`}
                        onClick={() => setSelectedResultId(p.id)}
                    >
                        <img src={p.imageUrl} width="50" alt={p.nameEn} />
                        <span>{p.nameEn} ({p.type}{p.type2 ? `/${p.type2}` : ""})</span>

                        {selectedResultId === p.id && (
                            <button onClick={(e) => {
                                e.stopPropagation();
                                addPokemon(p);
                            }}>Add to Team</button>
                        )}
                    </div>
                ))}
            </div>
        </div>
    );
}

export default App;