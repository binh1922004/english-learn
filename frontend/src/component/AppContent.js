import React, {Component} from "react";
import Buttons from "./Buttons";
import LoginForm from "./LoginForm";
import WelcomeContent from "./WelcomContent";
import AuthContent from "./AuthContent";
import {request, setAuthHeader} from "../helpers/axios_helper"
import { useNavigate } from 'react-router-dom';

class AppContent extends Component{

    constructor(props) {
        super(props);
        this.state = {
            componentToShow: "welcome"
        }
    };

    login = () => {
        this.setState({componentToShow: "login"})
    };

    logout = () => {
        this.setState({componentToShow: "welcome"})
        setAuthHeader(null);
    };
    onLogin = (e, username, password) => {
        // this.props.navigate('/home')

        //tat cac hoat dong tu dong cua trinh duyet
        e.preventDefault();
        request(
            "POST",
            "/login",
            {
                username: username,
                password: password
            }).then(
            (response) => {
                setAuthHeader(response.data.result.token)
                this.setState({componentToShow: "login"})
                this.props.navigate('/home')
            }).catch(
            (error) => {
                setAuthHeader(null);
                this.setState({componentToShow: "welcome"})
            }
        );
    };

    onRegister = (event, username, password, name, email, dob) => {
        event.preventDefault();
        request(
            "POST",
            "/register",
            {                username: username,
                password: password,
                email: email,
                name: name,
                dob: dob
            }).then(
            (response) => {
                this.setState({componentToShow: "welcome"});
            }).catch(
            (error) => {
                setAuthHeader(null);
                this.setState({componentToShow: "welcome"})
            }
        );
    };
    render() {
        return (
            <>
                <Buttons
                    login={this.login}
                    logout={this.logout}
                />

                {this.state.componentToShow === "welcome" && <WelcomeContent /> }
                {this.state.componentToShow === "login" && <LoginForm onLogin={this.onLogin} onRegister={this.onRegister} />}
                {this.state.componentToShow === "messages" && <AuthContent />}
            </>
        )
    }
}

// HOC để inject navigate vào props
export default function WithNavigate(props) {
    const navigate = useNavigate();
    return <AppContent {...props} navigate={navigate} />;
}