import carousel1 from "../images/carousel_1.jpeg";
import carousel2 from "../images/carousel_2.jpeg";
import carousel3 from "../images/carousel_3.jpeg";

const Carousel = () => {
  return (
    <div
      id="carouselExampleIndicators"
      class="carousel slide mt-2"
      data-bs-ride="true"
    >
      <div class="carousel-indicators">
        <button
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide-to="0"
          class="active"
          aria-current="true"
          aria-label="Slide 1"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide-to="1"
          aria-label="Slide 2"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide-to="2"
          aria-label="Slide 3"
        ></button>
      </div>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src={carousel2} class="d-block w-100" alt="..." />
        </div>
        <div class="carousel-item">
          <img src={carousel1} class="d-block w-100" alt="..." />
        </div>
        <div class="carousel-item">
          <img src={carousel3} class="d-block w-100" alt="..." />
        </div>
      </div>
      <button
        class="carousel-control-prev" 
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide="prev"
        style={{justifyContent:"start", marginLeft:"1%"}}
      >
        <span class="carousel-control-prev-icon " aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button
        class="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide="next"
        style={{justifyContent:"end", marginRight:"1%"}}
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
  );
};

export default Carousel;
