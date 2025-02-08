import * as React from 'react';
import { request} from '../helpers/axios_helper';
import {useState} from "react";

const Pronounce = () => {
    const [vocabulary, setVocabulary] = useState([])
    const [currentIndex, setCurrentIndex] = useState(0)
    const [userAnswer, setUserAnswer] = useState("")
    const [results, setResults] = useState([])
    const [isSendRequest, setIsSendRequest] = useState(false);  // Thêm trạng thái kiểm soát việc nhấn nút

    if (!isSendRequest){
        request(
            "GET",
            "/word/random",
            []
        ).then((respone) => {
            setVocabulary(respone.data.result)
        }).catch(
            (error) => {
                console.log(error)
            }
        )
        setIsSendRequest(true)
    }

    function speakText(text) {
        if ('speechSynthesis' in window) {
            const synth = window.speechSynthesis;
            const utterance = new SpeechSynthesisUtterance(text);
            utterance.lang = 'en-US'; // Ngôn ngữ tiếng Anh (Mỹ)
            synth.speak(utterance);
        } else {
            alert('Trình duyệt của bạn không hỗ trợ phát âm thanh.');
        }
    }

    const handleSubmit = () => {
        // Nếu chưa nhập, dừng lại
        if (userAnswer.trim() === "") {
            alert("Hãy nhập câu trả lời trước khi tiếp tục!");
            return;
        }

        const currentWord = vocabulary[currentIndex];
        const isCorrect = userAnswer.trim().toLowerCase() === currentWord.word.toLowerCase();

        // Lưu kết quả
        setResults([
            ...results,
            {
                word: currentWord.word,
                correct: isCorrect,
            },
        ]);

        // Xóa câu trả lời đã nhập
        setUserAnswer("");

        // Chuyển sang từ tiếp theo sau khi nhấn nút
        setCurrentIndex(currentIndex + 1);
    };

    if (vocabulary.length === 0) {
        return <p className="text-center mt-5">Đang tải...</p>;
    }

    if (currentIndex >= vocabulary.length) {
        // Hiển thị tổng kết
        const correctCount = results.filter((result) => result.correct).length;
        return (
            <>
                <div className="container mt-5">
                    <h1 className="text-center text-success">Hoàn thành!</h1>
                    <p className="text-center">
                        Bạn đã trả lời
                        đúng <strong>{correctCount}</strong> trên <strong>{vocabulary.length}</strong> từ.
                    </p>
                    <ul className="list-group">
                        {results.map((result, index) => (
                            <li
                                key={index}
                                className={`list-group-item ${
                                    result.correct ? "list-group-item-success" : "list-group-item-danger"
                                }`}
                            >
                                {result.word}: {result.correct ? "Đúng" : "Sai"}
                            </li>
                        ))}
                    </ul>
                </div>
                <div className="container mt-5">
                    <button className="btn-primary"> Back home</button>
                </div>
            </>


        );
    }

    const currentWord = vocabulary[currentIndex];

    return (
        <div className="container mt-5">
            <h1 className="text-center">Luyện tập từ vựng</h1>
            <div className="card mx-auto mt-3" style={{maxWidth: "500px"}}>
                <div className="card-body">
                    <h5 className="card-title">Hãy lắng nghe và nhập từ đúng:</h5>
                    <button
                        className="btn btn-light ms-2"
                        onClick={() => speakText(currentWord.word)}
                        aria-label="Phát âm"
                        title="Phát âm"
                    >
                        <i className="fas fa-volume-up"></i> {/* Icon loa */}
                    </button>
                    <input
                        type="text"
                        className="form-control mb-3"
                        placeholder="Nhập từ tiếng Anh"
                        value={userAnswer}
                        onChange={(e) => setUserAnswer(e.target.value)}
                        onKeyDown={(e) => {
                            if (e.key === "Enter") {
                                handleSubmit(); // Gọi hàm xử lý khi nhấn Enter
                            }
                        }}
                    />
                    <button
                        className="btn btn-primary w-100"
                        onClick={handleSubmit}
                    >
                        Gửi
                    </button>
                </div>
            </div>
        </div>
    );
}

export default Pronounce;