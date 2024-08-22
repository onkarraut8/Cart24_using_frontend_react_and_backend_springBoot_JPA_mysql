import { useState } from "react";
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer, toast } from "react-toastify";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const ChangePass = () => {
    let navigate = useNavigate();
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

   const [loginRequest, setLoginRequest] = useState({
        emailId: emailId,
        phoneNo: phoneNo,
    });

    const [message, setMessage] = useState(" ");
    const [message1, setMessage1] = useState(" ");

    const [show, setShow] = useState(false);

    const [users, setUser] = useState({
        emailId:emailId,
        password: "",
        otp: "",
    });

    const handleUserInput = (e) => {
        setLoginRequest({ ...loginRequest, [e.target.name]: e.target.value });
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


    const savePass = (e) => {
        e.preventDefault();
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



    const loginAction = (e) => {
        e.preventDefault();
        setMessage("Please wait while varifying emailId and mobile number...");
        fetch("http://localhost:8080/api/user/forgotpass", {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            body: JSON.stringify(loginRequest),
        }).then((result) => {
            console.log("result", result);
            result.json().then((res) => {
                console.log(res);
            }).catch(() => {
                toast.error('Enter valid Register mailId and Mobile Number');
            });
        });

    };

    const validate = (e) => {
        e.preventDefault();
        fetch("http://localhost:8080/api/user/forgot/validateotp?Otp=" + users.otp, { method: "POST", }).then((result) => {
            console.log("result@@@@@" + result.status);

            if (result.status === 200) {
                console.log("result@@@@@=done");
                setShow(true);
                setMessage1("OTP Varified");
            }
        }).catch(() => {
            setMessage1("OTP is incorrect");
            toast.error('Enter OTP is Invalid');
            navigate("/user/changepass1");
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
                            <div style={{ display: "" }}>
                                <div className="mb-3 text-color">
                                    <label for="emailId" class="form-label">
                                        <b>Mobile</b>
                                    </label>
                                    <input
                                        type="text"
                                        pattern="[789][0-9]{9}"
                                        className="form-control"
                                        id="phoneNo"
                                        name="phoneNo"
                                        maxlength="10"
                                        title="Please enter valid mobile number of 10 digit"
                                        onChange={handleUserInput}
                                        value={loginRequest.phoneNo}
                                        placeholder="Enter Mobile Number"
                                        required
                                        readOnly
                                    />
                                   {/*  <span style={{ color: "red" }}>{message}</span> */}
                                </div>

                                <div class="text-color">
                                    <b><label for="email" class="form-label" style={{ marginBottom: "0" }}>
                                        Email Id
                                    </label></b>
                                    <div class="row card-body" style={{ marginTop: "0" }}>
                                        <input
                                            type="email"
                                            style={{ width: "70%" }}
                                            className="form-control"
                                            id="emailId"
                                            name="emailId"
                                            pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
                                            title="Enter Valid email"
                                            onChange={handleUserInput}
                                            value={loginRequest.emailId}
                                            placeholder="Enter EmailId"
                                            required
                                            readOnly
                                        />
                                        <button
                                            style={{ width: "30%", display: "inline" }}
                                            onClick={loginAction}
                                            class="btn btn-light bg-color custom-bg-text"
                                        >
                                            Send OTP
                                        </button>
                                    </div>
                                    <span style={{ color: "red" }}>{message}</span>
                                </div>
                                <div class="text-color">
                                    <b><label for="otp" class="form-label" style={{ marginBottom: "0" }}>
                                        Varify OTP
                                    </label></b>
                                    <div class="row card-body" style={{ marginTop: "0" }}>
                                        <input
                                            type="text"
                                            class="form-control"
                                            id="otp"
                                            style={{ width: "70%" }}
                                            name="otp"
                                            title=""
                                            placeholder="Enter OTP"
                                            onChange={handleUserInput}
                                            value={users.otp}
                                            required
                                        />
                                        <button
                                            style={{ width: "30%", display: "inline" }}
                                            onClick={validate}
                                            class="btn btn-light bg-color custom-bg-text"
                                        >
                                            Varify OTP
                                        </button>
                                        <span style={{ color: "red"}}>{message1}</span>
                                    </div>
                                </div>
                            </div>
                            <div style={{ display: show ? "" : "none" }}>
                                <div class="mb-3 text-color">
                                    <b><label for="quantity" class="form-label ">
                                        New Password
                                    </label></b>
                                    <input
                                        type="password"
                                        class="form-control"
                                        id="password"
                                        name="password"
                                        pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                        title="Must contain at least one  number,at least one special character, one uppercase and lowercase letter, and at least 8 or more characters"
                                        onChange={handleUserInput}
                                        placeholder="Enter New Password"
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
