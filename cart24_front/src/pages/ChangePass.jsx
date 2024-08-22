import { useState } from "react";
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer, toast } from "react-toastify";
import axios from "axios";


const ChangePass = () => {

    let user = JSON.parse(sessionStorage.getItem("active-user"));
    const admin = JSON.parse(sessionStorage.getItem("active-admin"));
    const deliveryPerson = JSON.parse(sessionStorage.getItem("active-delivery"));

    var id = 0;
    var role = "";
    var emailId = "";
    var phoneNo = "";

    if (user != null) {
        id = user.id;
        emailId = user.emailId;
        phoneNo = user.phoneNo;
        role = user.role;
    } else if (admin != null) {
        id = admin.id;
        emailId = admin.emailId;
        phoneNo = admin.phoneNo;
        role = admin.role;
    } else if (deliveryPerson != null) {
        id = deliveryPerson.id;
        emailId = deliveryPerson.emailId;
        phoneNo = deliveryPerson.phoneNo;
        role = deliveryPerson.role;
    } else {
        id = 0;
        emailId = "";
        phoneNo = "";
        role = "";
    }

    const [users, setUser] = useState({

        password: "",

    });

    const handleUserInput = (e) => {
        setUser({ ...users, [e.target.name]: e.target.value });
    };


    const [cpass, setCpass] = useState({
        confirmpassword: "",
    });

    const [error, setError] = useState({
        err: "",
    });


    const Changepass = (e) => {

        e.preventDefault();
        let inputError = {
            err: ""
        };
        if (user == null && admin == null && deliveryPerson == null) {
            alert("Please login!!!");
            e.preventDefault();
        }
        else if (cpass.confirmpassword === users.password) {
            savePass();
            window.location.reload();
            e.preventDefault();
        }
        else {
            console.log("pasword: " + users.password + " Conp: " + cpass.Conpass);
            setError({ ...inputError, err: "Password and confirm pasword should be same" })
            return
        }
    };


    const savePass = () => {

        const formData = new FormData();
        formData.append("id", id);
        formData.append("emailId", emailId);
        formData.append("password", users.password);
        formData.append("role", role);
        formData.append("phoneNo", phoneNo);
        console.log("Email:" + emailId + " role:" + role + " password:" + users.password + " phoneNo:" + phoneNo);


        axios
            .post("http://localhost:8080/api/user/change", formData)
            .then((resp) => {
                let result = resp.data.data;
                alert("Password Change successfully");
                console.log(result);
            })
            .catch((error) => {
                console.log("Error", error);
                alert("Error saving password");
            });
        toast.success("Password Change successfully", {
            position: "top-center",
            autoClose: 1000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
        });

    };



    return (
        <div>
            <div class="mt-2 d-flex aligns-items-center justify-content-center ms-2 me-2 mb-2">
                <div
                    class="card border-color text-color custom-bg"
                    style={{ width: "30rem" }}
                >
                    <div className="card-header bg-color custom-bg-text text-center">
                        <h5 class="card-title">Change Password</h5>
                    </div>
                    <div class="card-body">
                        <form onSubmit={Changepass}>
                            <div style={{ display: "none" }}>
                                <div class="text-color">
                                    <b><label for="email" class="form-label" style={{ marginBottom: "0" }}>
                                        Email Id
                                    </label></b>
                                    <div class="row card-body" style={{ marginTop: "0" }}>
                                        <input
                                            type="email"
                                            class="form-control"
                                            placeholder="Enter EmailId"
                                            id="emailId"
                                            style={{ width: "70%" }}
                                            name="emailId"
                                            title=""
                                            /* onChange={handleUserInput}
                                            value={users.password} */
                                            required
                                        />
                                        <button
                                            style={{ width: "30%", display: "inline" }}
                                            /* onClick={saveCategory} */
                                            class="btn btn-light bg-color custom-bg-text"
                                        >
                                            Send OTP
                                        </button>
                                    </div>
                                </div>
                                <div class="text-color">
                                    <b><label for="otp" class="form-label" style={{ marginBottom: "0" }}>
                                        Varify OTP
                                    </label></b>
                                    <div class="row card-body" style={{ marginTop: "0" }}>
                                        <input
                                            type="text"
                                            class="form-control"
                                            placeholder="Enter OTP"
                                            id="otp"
                                            style={{ width: "70%" }}
                                            name="otp"
                                            title=""
                                            /* onChange={handleUserInput}
                                            value={users.password} */
                                            required
                                        />
                                        <button
                                            style={{ width: "30%", display: "inline" }}
                                            /* onClick={saveCategory} */
                                            class="btn btn-light bg-color custom-bg-text"
                                        >
                                            Varify OTP
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div style={{ display: "" }}>
                                <div class="mb-3 text-color">
                                    <b><label for="quantity" class="form-label ">
                                        New Password
                                    </label></b>
                                    <input
                                        type="password"
                                        class="form-control"
                                        id="password"
                                        name="password"
                                        placeholder="Enter New Password"
                                        pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                        title="Must contain at least one  number,at least one special character, one uppercase and lowercase letter, and at least 8 or more characters"
                                        onChange={handleUserInput}
                                        value={users.password}
                                        required
                                    />
                                </div>

                                <div class="mb-3 text-color">
                                    <b>
                                        <label for="quantity" class="form-label ">
                                            Confirm New Password
                                        </label>
                                    </b>
                                    <input
                                        type="password"
                                        class="form-control"
                                        id="confirmpassword"
                                        name="confirmpassword"
                                        onChange={e => setCpass({ ...cpass, [e.target.name]: e.target.value })}
                                        placeholder="Confirm Password"
                                        required
                                    />
                                    <p className="error-message">{error.err}</p>
                                </div>


                                <input
                                    type="submit"
                                    class="btn btn-light bg-color custom-bg-text"
                                    value="Change Password"
                                />
                            </div>
                            <ToastContainer />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ChangePass;
