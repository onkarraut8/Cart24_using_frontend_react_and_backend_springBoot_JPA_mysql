import Header from "./pages/Header";
import Footer from "./pages/Footer";
import NotFound from "./pages/NotFound";
import AddProductForm from "./productComponent/AddProductForm";
import ShowProduct from "./productComponent/ShowProduct";
import UpdateProduct from "./productComponent/UpdateProduct";
import { Routes, Route } from "react-router-dom";
import ContactUs from "./pages/ContactUs";
import ChangePass from "./pages/ChangePass";
import ChangePass1 from "./pages/ChangePass1";
import AboutUs from "./pages/AboutUs";
import AddCategoryForm from "./productComponent/AddCategoryForm";
import ShowCategories from "./productComponent/ShowCategories";
import DeletedCategories from "./productComponent/DeletedCategories";
import ImageGallery from "./productComponent/ProductImageGalary";
import UpdateProfile from "./userComponent/UpdateProfile";
import HomePage from "./pages/HomePage";
import Links from "./pages/Links";
import Product from "./pages/Product";
import DeletedProduct from "./productComponent/DeletedProduct";
import AddUserForm from "./userComponent/AddUserForm";
import VarifyUser from "./userComponent/VarifyUser";
import ContactedUser from "./userComponent/ContactedUser";
import AdminDashBoard from "./userComponent/AdminDashBoard";
import AddAdminForm from "./userComponent/AddAdminForm";
import AddDeleveryForm from "./userComponent/AddDeleveryPersonForm";
import UserLoginForm from "./userComponent/UserLoginForm";
import ADLoginForm from "./userComponent/ADLoginForm";
import DLoginForm from "./userComponent/DLoginForm";
import ForgotPassword from "./userComponent/ForgotPassword1";
import ForgotPassword1 from "./userComponent/ForgotPassword";
import ValidateOtp from "./userComponent/ValidateOtp";
import ChangePassword from "./userComponent/ChangePassword";
import MyCart from "./userComponent/MyCart";
import AddCardDetails from "./pages/AddCardDetails";
import MyOrder from "./userComponent/MyOrder";
import AllOrders from "./userComponent/AllOrders";
import UpdateOrderDelivery from "./userComponent/UpdateOrderDelivery";

import AssignDeliveryToOrders from "./userComponent/AssignDeliveryToOrders";
import MyDeliveries from "./userComponent/MyDeliveries";

function App() {
  return (
    <div>
      <Header />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/admin/links" element={<Links />} />
        <Route path="/home/all/product/categories" element={<HomePage />} />
        <Route path="contact" element={<ContactUs />} />
        <Route path="about" element={<AboutUs />} />
        <Route path="addproduct" element={<AddProductForm />} />
        <Route path="/showproduct" element={<ShowProduct />} />
        <Route path="/updateproduct" element={<UpdateProduct />} />
        <Route path="product/deletedproduct" element={<DeletedProduct />} />
        <Route path="addcategory" element={<AddCategoryForm />} />
        <Route path="showcategories" element={<ShowCategories />} />
        <Route path="/category/deletedcategory" element={<DeletedCategories />} />
        <Route path="varifyuser" element={<VarifyUser />} />
        <Route path="contacteduser" element={<ContactedUser />} />
        <Route path="/user/changepass" element={<ChangePass />} />
        <Route path="/user/changepass1" element={<ChangePass1 />} />
        <Route path="/users/update" element={<UpdateProfile />} />
        <Route path="/product" element={<Product />} />
        <Route path="/product/gallery" element={<ImageGallery />} />
        <Route path="/user/register" element={<AddUserForm />} />
        <Route path="/user/admin/dashboard" element={<AdminDashBoard />} />
        <Route path="/user/register/admin" element={<AddAdminForm />} />
        <Route path="/user/register/delivery" element={<AddDeleveryForm />} />
        <Route path="/user/login" element={<UserLoginForm />} />
        <Route path="/user/login/ADLogin" element={<ADLoginForm />} />
        <Route path="/user/login/DLogin" element={<DLoginForm />} />
        <Route path="/user/login/forgotpass" element={<ForgotPassword />} />
        <Route path="/user/login/forgotpass1" element={<ForgotPassword1 />} />
        <Route path="/user/login/forgot/validate" element={<ValidateOtp />} />
        <Route path="/user/login/forgot/validate/change" element={<ChangePassword />} />
        <Route path="/home/product/category/:categoryId/:categoryName" element={<HomePage />} />
        {/* <Route path="/home/product/search/:search" element={<HomePage />} /> */}
        <Route path="/product/:productId/category/:categoryId" element={<Product />} />
        <Route path="/user/mycart" element={<MyCart />} />
        <Route path="/user/order/payment" element={<AddCardDetails />} />

        <Route path="/user/myorder" element={<MyOrder />} />
        <Route path="/user/admin/allorder" element={<AllOrders />} />
        <Route path="/user/admin/searchOrder" element={<UpdateOrderDelivery />} />
        <Route path="/user/admin/assigndelivery" element={<AssignDeliveryToOrders />} />
        <Route path="/user/delivery/myorders" element={<MyDeliveries />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
      <Footer/>

    </div>
  );
}

export default App;
