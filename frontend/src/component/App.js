import './App.css';
import AppContent from "./AppContent";
import Home from "./Home";
import {Routes, Route, BrowserRouter} from "react-router-dom";
import Practice from "./Practice"
function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/english-learn/home" element={<Home/>} />
                <Route path="/english-learn" element={<AppContent/>} />
                <Route path="/english-learn/practice" element={<Practice/>} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;