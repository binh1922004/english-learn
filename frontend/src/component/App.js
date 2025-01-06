import './App.css';
import AppContent from "./AppContent";
import Home from "./Home";
import Practice from "./Practice"
import {Routes, Route } from "react-router-dom";

function App() {
    return (
        <Routes>
            <Route path="/home" element={<Home />} />
            <Route path="/" element={<AppContent />} />
            <Route path="/practice" element={<Practice />} />
        </Routes>
    );
}

export default App;