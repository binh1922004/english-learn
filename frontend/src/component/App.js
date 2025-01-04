import './App.css';
import AppContent from "./AppContent";
import Home from "./Home";
import {Routes, Route, BrowserRouter} from "react-router-dom";
function App() {
  return (
      <BrowserRouter>
          <Routes>
              <Route path="/home" element={<Home/>} />
              <Route path="/" element={<AppContent/>} />
          </Routes>
      </BrowserRouter>
  );
}

export default App;
