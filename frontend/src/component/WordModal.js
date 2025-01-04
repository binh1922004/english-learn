import React, { useState } from "react";
import {request, setAuthHeader} from "../helpers/axios_helper"
const VocabularyAddModal = () => {
    const [word, setWord] = useState("");
    const [meaning, setMeaning] = useState("");
    const [example, setExample] = useState("");

    const handleSave = () => {
        console.log("Từ vựng:", word);
        console.log("Nghĩa:", meaning);
        console.log("Ví dụ:", example);

        request(
            "POST",
            "/word/add",
            {
                word: word,
                definition: meaning,
                example: example
            })
            .then(
                (response) => {
                    const data = response.data;
                    console.log(data)
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
        // Reset form sau khi lưu
        setWord("");
        setMeaning("");
        setExample("");
    };

    return (
        <div className="container mt-5">
            {/* Nút mở popup */}
            <button
                type="button"
                className="btn btn-primary"
                data-bs-toggle="modal"
                data-bs-target="#vocabularyModal"
            >
                Thêm từ vựng
            </button>

            {/* Modal */}
            <div
                className="modal fade"
                id="vocabularyModal"
                tabIndex="-1"
                aria-labelledby="vocabularyModalLabel"
                aria-hidden="true"
            >
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="vocabularyModalLabel">
                                Thêm Từ Vựng
                            </h5>
                            <button
                                type="button"
                                className="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Close"
                            ></button>
                        </div>
                        <div className="modal-body">
                            {/* Form điền thông tin */}
                            <form>
                                <div className="mb-3">
                                    <label htmlFor="wordInput" className="form-label">
                                        Từ vựng
                                    </label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        id="wordInput"
                                        placeholder="Nhập từ vựng"
                                        value={word}
                                        onChange={(e) => setWord(e.target.value)}
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="meaningInput" className="form-label">
                                        Nghĩa
                                    </label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        id="meaningInput"
                                        placeholder="Nhập nghĩa của từ"
                                        value={meaning}
                                        onChange={(e) => setMeaning(e.target.value)}
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="exampleInput" className="form-label">
                                        Ví dụ
                                    </label>
                                    <textarea
                                        className="form-control"
                                        id="exampleInput"
                                        rows="3"
                                        placeholder="Nhập ví dụ"
                                        value={example}
                                        onChange={(e) => setExample(e.target.value)}
                                    ></textarea>
                                </div>
                            </form>
                        </div>
                        <div className="modal-footer">
                            <button
                                type="button"
                                className="btn btn-secondary"
                                data-bs-dismiss="modal"
                            >
                                Hủy
                            </button>
                            <button
                                type="button"
                                className="btn btn-primary"
                                data-bs-dismiss="modal"
                                onClick={handleSave}
                            >
                                Lưu
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default VocabularyAddModal;
