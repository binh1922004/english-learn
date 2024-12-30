import * as React from 'react';

import { request, setAuthHeader } from '../helpers/axios_helper';

export default class AuthContent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            code: 0,
            result: ""
        }
    };

    componentDidMount() {
        request(
            "GET",
            "/message",
            {})
            .then(
            (response) => {
                const data = response.data;
                this.setState({code: data.code, result: data.result})
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
            <div className="row justify-content-md-center">
                <div className="col-4">
                    <div className="card" style={{width: "18rem"}}>
                        <div className="card-body">
                            <h5 className="card-title">Backend response</h5>
                            <p className="card-text">Code: {this.state.code}</p>
                            <p className="card-text">Result: {this.state.result}</p>
                        </div>
                    </div>
                </div>
            </div>
        );
    };
}