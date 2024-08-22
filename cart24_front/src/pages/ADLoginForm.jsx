import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";

const UserLoginFrom = () => {
  let navigate = useNavigate();

  const [loginRequest, setLoginRequest] = useState({
    emailId: "",
    password: "",
    role: "Customer",
  });


  const handleUserInput = (e) => {
    setLoginRequest({ ...loginRequest, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    sessionStorage.clear();
  }, []);

  const ProceedLogin = (e) => {
    e.preventDefault();
    if (validate()) {
      ///implentation
      // console.log('proceed');
      fetch("http://localhost:8080/api/user/login", {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify(loginRequest),
      }).then((res) => {
        return res.json();
      }).then((resp) => {
        //console.log(resp)
        console.log("Email: " + resp.emailId + "Password: " + resp.password + "Role" + resp + "Lower Email: " + loginRequest.emailId.toLowerCase());
        if (resp.role === loginRequest.role && resp.emailId === loginRequest.emailId.toLowerCase()) {
          toast.success('Success');
          if (resp.role === "Admin") {
            console.log("Working fine:");
            sessionStorage.setItem("active-admin", JSON.stringify(resp));
          } else if (resp.role === "Customer") {
            sessionStorage.setItem("active-user", JSON.stringify(resp));
          } else if (resp.role === "Delivery") {
            sessionStorage.setItem("active-delivery", JSON.stringify(resp));
          }
          navigate('/')
          window.location.reload(false);
        } else {
          toast.error('Please Enter valid credentials');

        }

      }).catch(() => {
        toast.error('Login Failed due to incorrect password :');
      });
    }
  }


  const validate = () => {
    let result = true;
    if (loginRequest.emailId === '' || loginRequest.emailId === null) {
      result = false;
      toast.warning('Please Enter Username');
    }
    if (loginRequest.password === '' || loginRequest.password === null) {
      result = false;
      toast.warning('Please Enter Password');
    }
    return result;
  }

  return (
    <div>
      <div className="mt-2 d-flex aligns-items-center justify-content-center">
        <div
          className="card border-color custom-bg"
          style={{ width: "25rem" }}
        >
          <div className="card-header bg-color text-center custom-bg-text">
            <h4 className="card-title">Login</h4>
          </div>
          <div className="card-body">
            <form onSubmit={ProceedLogin}>
              <div class="mb-3 text-color">
                <label for="role" class="form-label">
                  <b>User Role</b>
                </label>
                <select
                  onChange={handleUserInput}
                  className="form-control"
                  name="role"
                >
                  <option value="0">Select Role</option>
                  <option value="Admin"> Admin </option>
                  <option value="Customer" selected> Customer </option>
                  <option value="Delivery"> Delivery Person </option>
                </select>
              </div>

              <div className="mb-3 text-color">
                <label for="emailId" class="form-label">
                  <b>Email Id</b><span className="errmsg">*</span>
                </label>
                <input
                  type="email"
                  className="form-control"
                  id="emailId"
                  name="emailId"
                  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
                  title="Enter Valid email"
                  onChange={handleUserInput}
                  value={loginRequest.emailId}
                />

              </div>
              <div className="mb-3 text-color">
                <label for="password" className="form-label">
                  <b>Password</b><span className="errmsg">*</span>
                </label>
                <input
                  type="password"
                  className="form-control"
                  id="password"
                  name="password"
                  pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*?[!@#$%^&*+`~=?\|<>/]).{8,}"
                  title="Must contain at least one  number,at least one special character, one uppercase and lowercase letter, and at least 8 or more characters"
                  onChange={handleUserInput}
                  value={loginRequest.password}
                  autoComplete="on"

                />{/* <i id="togglePassword" style={{marginLeft:"-40px", marginTop:"px",fontSize:"20px", cursor: "pointer",}}>&#128065;</i></div> */}
                
              </div>
              <button
                type="submit"
                className="btn btn-light bg-color custom-bg-text"
              >
                Login
              </button>
              <ToastContainer />
              <div align="justify" class="container-fluid navbar-bar mt-3">
                <ul class="navbar mb-1 me-auto">
                  <li class="nav-link">
                    <Link to="/user/login/forgotpass" class="nav-link active" aria-current="page">
                      <b className="text-color btn btn-light bg-color custom-bg-text ">Forgot Password</b>
                    </Link></li>
                  <li class="nav-link">
                    <Link to="/user/register" class="nav-link active" aria-current="page">
                      <b className="text-color btn btn-light bg-color custom-bg-text ">Register</b>
                    </Link></li></ul></div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserLoginFrom;
