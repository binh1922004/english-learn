import './App.css';
import AppContent from "./AppContent";
import Home from "./Home";
import Practice from "./Practice"
import { HashRouter, Routes, Route } from "react-router-dom";

function App() {
    return (
        <HashRouter basename={"english-learn"}>
            <Routes>
                <Route path="/home" element={<Home />} />
                <Route path="/" element={<AppContent />} />
                <Route path="/practice" element={<Practice />} />
            </Routes>
        </HashRouter>
    );
}

export default App;