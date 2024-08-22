import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const AdminHeader = () => {
  let navigate = useNavigate();
  const user = JSON.parse(sessionStorage.getItem("active-admin"));
  console.log("#####%%%%%" + user.firstName);

  const adminLogout = () => {
    toast.success("logged out!!!", {
      position: "top-center",
      autoClose: 1000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
    });
    sessionStorage.removeItem("active-admin");
    navigate("/user/login/ADLogin");
    window.location.reload(false);
  };

  return (
    <ul className="navbar-nav mb-2 mb-lg-0 me-1 d-flex justify-content-end">

      <li className="nav-item">
        <Link to="/admin/links" className="nav-link active">
          <b className="custom-bg-text">Links</b>
        </Link>
      </li>

      <li class="nav-item dropdown custom-bg-text me-2">
        <a class="nav-link dropdown-toggle text-white" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          <b>Users</b>
        </a>
        <ul class="dropdown-menu custom-bgs ">
          <li><a class="dropdown-item" href="#">
            <Link to="/contacteduser" className="nav-link active" aria-current="page">
              <b className=""> Contacted-User</b>
            </Link></a></li>
          <li><a class="dropdown-item" href="#">
            <Link to="/varifyuser" className="nav-link active" aria-current="page">
              <b className=""> Varify-Users</b>
            </Link></a></li>
        </ul>
      </li>

      <li class="nav-item dropdown text-color me-2">
        <a class="nav-link dropdown-toggle text-white" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          <b>Category</b>
        </a>
        <ul class="dropdown-menu custom-bgs ">
          <li><a class="dropdown-item" href="#">
            <Link to="/addcategory" className="nav-link active" aria-current="page">
              <b className=""> Add-Category</b>
            </Link></a></li>
          <li><a class="dropdown-item" href="#">
            <Link to="/showcategories" className="nav-link active" aria-current="page">
              <b className=""> Show-Category</b>
            </Link></a>
          </li>
          <li className="dropdown-item" href="#">
            <Link to="/category/deletedcategory" className="nav-link active" aria-current="page">
              <b className="">Deleted-Category</b>
            </Link>

          </li>
        </ul>
      </li>

      <li class="nav-item dropdown text-color me-2">
        <a class="nav-link dropdown-toggle text-white" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          <b>Product</b>
        </a>
        <ul class="dropdown-menu custom-bgs ">
          <li><a class="dropdown-item" href="#">
            <Link to="/addproduct" className="nav-link active" aria-current="page">
              <b className="">Add-Product</b>
            </Link></a>
          </li>
          <li><a class="dropdown-item" href="#">
            <Link to="/showproduct" className="nav-link active" aria-current="page">
              <b className="">Show-Product</b>
            </Link></a>
          </li>
          <li><a class="dropdown-item" href="#">
            <Link to="/updateproduct" className="nav-link active" aria-current="page">
              <b className="">Update-Product</b>
            </Link></a>
          </li>
          <li className="dropdown-item" href="#">
            <Link to="/product/gallery" className="nav-link active" aria-current="page">
              <b className="">Gallery</b>
            </Link>

          </li>
          <li className="dropdown-item" href="#">
            <Link to="/product/deletedproduct" className="nav-link active" aria-current="page">
              <b className="">Deleted-Product</b>
            </Link>

          </li>
        </ul>
      </li>

      <li class="nav-item dropdown text-color me-2">
        <a class="nav-link dropdown-toggle text-white" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          <b>Order</b>
        </a>
        <ul class="dropdown-menu custom-bgs ">
          <li><a class="dropdown-item" href="#">
            <Link to="/user/admin/allorder" className="nav-link active" aria-current="page">
              <b className="">All-Orders</b>
            </Link></a></li>
          <li><a class="dropdown-item" href="#">
            <Link to="/user/admin/assigndelivery" className="nav-link active" aria-current="page">
              <b className="">Assign-Delivery-Person</b>
            </Link></a></li>
        </ul>
      </li>

      <li class="nav-item dropdown text-color me-2">
        <a class="nav-link dropdown-toggle text-white" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          <b>Change Password </b>
        </a>
        <ul class="dropdown-menu custom-bgs ">
          <li><a class="dropdown-item" href="#">
            <Link to="/user/changepass" className="nav-link active" aria-current="page">
              <b className="">Change Password </b>
            </Link></a></li>
          <li><a class="dropdown-item" href="#">
            <Link to="/user/changepass1" className="nav-link active" aria-current="page">
              <b className="">Change Password1 </b>
            </Link></a></li>
            <li><a class="dropdown-item" href="#">
            <Link to="/user/login/forgotpass" className="nav-link active" aria-current="page">
              <b className="">Forgot Password </b>
            </Link></a></li>
          <li><a class="dropdown-item" href="#">
            <Link to="/user/login/forgotpass1" className="nav-link active" aria-current="page">
              <b className="">Forgot Password1 </b>
            </Link></a></li>
        </ul>
      </li>


      <li className="nav-item">
        <Link to="/users/update" className="nav-link active">
          <b className="custom-bg-text">Update Profile</b>
        </Link>

      </li>
      
      <li className="nav-item">
        <Link to="/user/admin/dashboard" className="nav-link active">
          <b className="custom-bg-text"> Dashboard </b>
        </Link>
      </li>

      <li className="nav-item">
        <Link to="" className="nav-link active" aria-current="page" onClick={adminLogout}>
          <b className="custom-bg-text">[{user.firstName} {user.lastName}] Logout</b>
        </Link>
        <ToastContainer />
      </li>
    </ul>
  );
};

export default AdminHeader;
