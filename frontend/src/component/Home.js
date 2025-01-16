import * as React from 'react';
import { request, setAuthHeader } from '../helpers/axios_helper';
import WordModal from "./WordModal";

export default class Home extends React.Component{
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
                    {/*<button className="btn btn-primary mb-3" onClick={() => this.setState({active: "true"})}>Thêm từ vựng</button>*/}
                    <a className="btn btn-secondary" href={"/practice"}>Luyện tập từ vựng</a>
                    <br/>
                    <a className="btn btn-outline-primary" href={"/pronounce"}>Luyện tập phát âm</a>
                </div>

            </>
        )
    }
}