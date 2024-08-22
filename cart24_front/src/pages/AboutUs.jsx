import about from "../images/about_1.jpg";
import { Link } from "react-router-dom";

const AboutUs = () => {
  return (
    <div>


      <div className="bod,dev ">
        <div className="about-section">
          <div><h1>About Us</h1></div>
          <p>Some text about who we are and what we do.</p>

        </div>

        <div><h2 className="hea">Our Team</h2></div>
        <div className="row d-flex justify-content-center">
          <div className="column">
            <div className="card">
              <img src={about} alt="Abc" className="imge" />
              <div className="container">
                <div><h2>Abc Def</h2></div>
                <p className="title">CEO & Founder</p>
                <p>Some text that describes me.</p>
                <p>abc@example.com</p>
                <p><Link to="/contact"><button className="button custom-bg-text">  Contact</button></Link></p>
              </div>
            </div>
          </div>

          <div className="column">
            <div className="card">
              <img src={about} alt="Abc" className="imge" />
              <div className="container">
                <div><h2>Abc Def</h2></div>
                <p className="title">Art Director</p>
                <p>Some text that describes me.</p>
                <p>abc@example.com</p>
                <p><Link to="/contact"><button className="button custom-bg-text">  Contact</button></Link></p>
              </div>
            </div>
          </div>

          <div className="column">
            <div className="card">
              <img src={about} alt="Abc" className="imge" />
              <div className="container">
                <div><h2>Abc Def</h2></div>
                <p className="title">Designer</p>
                <p>Some text that describes me.</p>
                <p>abc@example.com</p>
                <p><Link to="/contact"><button className="button custom-bg-text">  Contact</button></Link></p>
              </div>
            </div>
          </div>

          <div className="column">
            <div className="card">
              <img src={about} alt="Abc" className="imge" />
              <div className="container">
                <div><h2>Abc Def</h2></div>
                <p className="title">Designer</p>
                <p>Some text that describes me.</p>
                <p>abc@example.com</p>
                <p><Link to="/contact"><button className="button custom-bg-text">  Contact</button></Link></p>
              </div>
            </div>
          </div>
        </div>
      </div>



      <div className="dev">
        <div className="me-4 ms-4 mt-3">
          <b>
            Online shopping is a process whereby consumers directly buy goods, services etc. from a seller without an intermediary service over the Internet. Shoppers can visit web stores from the comfort of their house and shop as by sitting in front of the computer.Ecommerce, also known as electronic commerce or internet commerce, refers to the buying and selling of goods or services using the internet, and the transfer of money and data to execute these transactions.

            <br />
            <br />
            In existing system shopping can done in a manual way, the customer has to go for shopping, and then he is having the possibility to choose the products what ever he wants. Selling online also has its advantages when it comes to convincing customers you're the best in the industry. Your website can inform customers about your sales, the quality of your products, and why they should buy from you. You can also show customer reviews, so people know they're buying from a reputable brand. Doing business electronically describes e‐commerce. E-commerce (EC), an abbreviation for electronic commerce, is the buying and selling of goods and services, or the transmitting of funds or data, over an electronic network, primarily the internet.

            <br />
            <br />

            The online shopping system is fast gaining media for to sale or purchase items from anywhere and anytime. It is basically based on Internet, It is related with B2C (Business to Customer) model and status of the design and development of e-commerce platform.E-business or Online business means business transactions that take place online with the help of the internet. The term e-business came into existence in the year 1996. E-business is an abbreviation for electronic business. Therefore, the buyer and the seller do not meet personally. E-commerce is directly link to your business promotions, as it is the age of digital media. Making your business available online is crucial to your business development such as, highly convenience, wide exposure, global customer, easy to run, etc.

          </b>
        </div>
      </div>
    </div>

  );
};

export default AboutUs;
