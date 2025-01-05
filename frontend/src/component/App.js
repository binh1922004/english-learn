import './App.css';
import AppContent from "./AppContent";
import Home from "./Home";
import {Routes, Route, BrowserRouter} from "react-router-dom";
import Practice from "./Practice"
function App() {
  return (
      <BrowserRouter>
          <Routes>
              <Route path="/home" element={<Home/>} />
              <Route path="/" element={<AppContent/>} />
              <Route path="/practice" element={<Practice/>} />
          </Routes>
      </BrowserRouter>
  );
}

export default App;
