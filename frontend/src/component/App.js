import './App.css';
import AppContent from "./AppContent";
import Home from "./Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
function App() {
  return (
      <Router>
          <Routes>
              <Route path="/" element={<AppContent/>} />
              <Route path="/home" element={<Home/>} />
          </Routes>
      </Router>
  );
}

export default App;
