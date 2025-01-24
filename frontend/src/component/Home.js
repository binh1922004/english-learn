import * as React from 'react';
import { request, setAuthHeader } from '../helpers/axios_helper';
import WordModal from "./WordModal";
import { useNavigate } from 'react-router-dom';

class Home extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            code: 0,
            name: "",
            active: "false",
        }
    }

    componentDidMount() {
        request(
            "GET",
            "/users/info",
            {})
            .then(
                (response) => {
                    const data = response.data;
                    this.setState({code: data.code, name: data.result.name})
                })
            .catch(
                (error) => {
                    if (error.response.status === 401) {
                        setAuthHeader(null);
                    } else {
                        this.setState({data: error.response.code})
                    }

                }
            );
    };

    render() {
        return (
            <>
                <header className="d-flex justify-content-between align-items-center bg-light p-3">
                    <span className="fw-bold text-dark">{this.state.name}</span>
                    <span className="fw-bold text-dark">ELP</span>
                </header>
                <div className="d-flex flex-column align-items-center justify-content-center vh-100">
                    <div>
                        <WordModal />
                        <br/>
                    </div>
                    <button className="btn btn-primary mb-3" onClick={() => this.props.navigate('/practice')}>Luyện tập</button>
                </div>

            </>
        )

    }
}

// HOC để inject navigate vào props
export default function WithNavigate(props) {
    const navigate = useNavigate();
    return <Home {...props} navigate={navigate} />;
}