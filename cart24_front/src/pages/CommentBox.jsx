import cm from "../images/about_1.jpg";
import { useState, useEffect } from "react";

const CommentBox = (rev) => {
    const [cmt, setcmt] = useState(rev.item.review);
    const [fn, setfn] = useState(rev.item.user.firstName);
    const [ln, setln] = useState(rev.item.user.lastName);
    const [st, setst] = useState(rev.item.star);

    const empty =() => { setcmt(""); setfn(""); setln(""); setst(""); }
    
    return (
        <>
            <div className="col" onMouseOver={()=>{setcmt(rev.item.review);setst(rev.item.star);setfn(rev.item.user.firstName);setln(rev.item.user.lastName)}}>
                <div class="full-boxer">
                    <div class="comment-box">
                        <div class="box-top">
                            <div class="Profile">
                                <div class="profile-image">
                                    <img src={cm} />
                                </div>
                                <div class="Names">
                                    <span>{rev.item.user.firstName} {rev.item.user.lastName} </span>
                                    <span>{rev.item.user.emailId}</span>
                                </div>
                                <div class="Star">
                                    {/*  <span className="fs-5 lh-1"><b>{rev.item.star}</b></span> */}
                                    <span>{/* <StarRatingRead item={rev.item.star} /> */}
                                        <form  >
                                            <div class="rating">
                                                <input type='radio' hidden name='rate' id='rating-opt5' data-idx='0' disabled checked={rev.item.star === 5 ? true : false} />
                                                <label for='rating-opt5' ><span>Very satisfied</span></label>

                                                <input type='radio' hidden name='rate' id='rating-opt4' data-idx='1' disabled checked={rev.item.star === 4 ? true : false} />
                                                <label for='rating-opt4' ><span>Somewhat satisfied</span></label>

                                                <input type='radio' hidden name='rate' id='rating-opt3' data-idx='2' disabled checked={rev.item.star === 3 ? true : false} />
                                                <label for='rating-opt3' ><span>Neutral</span></label>

                                                <input type='radio' hidden name='rate' id='rating-opt2' data-idx='3' disabled checked={rev.item.star === 2 ? true : false} />
                                                <label for='rating-opt2' ><span>Dissatisfied</span></label>

                                                <input type='radio' hidden name='rate' id='rating-opt1' data-idx='4' disabled checked={rev.item.star === 1 ? true : false} />
                                                <label for='rating-opt1' ><span>Very Dissatisfied</span></label>
                                            </div>
                                        </form>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="comment d-flex justify-content-between">
                            <div className=""><span className=""><b>Comment:</b></span></div><div className=""><button data-bs-toggle="modal" data-bs-target="#exampleModalR"><b>Click here to View</b></button></div>
                        </div>
                        <div><p>{rev.item.review}</p></div>
                        {/*---------------------Review Modal----------------*/}
                        <div class="modal" id="exampleModalR" tabindex="-1" aria-labelledby="exampleModalLabelR" aria-hidden="true">
                            <div class="modal-dialog" >
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="exampleModalLabel">Review Product</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body m-4" style={{ width: "" }}>

                                        <div class="full-boxer">
                                            <div class="comment-box">
                                                <div class="box-top">
                                                    <div class="Profile">
                                                        <div class="profile-image">
                                                            <img src={cm} />
                                                        </div>
                                                        <div class="Names">
                                                            <span>{fn} {ln} </span>
                                                            <span>{rev.item.user.emailId}</span>
                                                        </div>
                                                        <div class="Star">
                                                            {/*  <span className="fs-5 lh-1"><b>{rev.item.star}</b></span> */}
                                                            <span>{/* <StarRatingRead item={rev.item.star} /> */}
                                                                <form  >
                                                                    <div class="rating">
                                                                        <input type='radio' hidden name='rate' id='rating-opt5' data-idx='0' disabled checked={st === 5 ? true : false} />
                                                                        <label for='rating-opt5' ><span>Very satisfied</span></label>

                                                                        <input type='radio' hidden name='rate' id='rating-opt4' data-idx='1' disabled checked={st === 4 ? true : false} />
                                                                        <label for='rating-opt4' ><span>Somewhat satisfied</span></label>

                                                                        <input type='radio' hidden name='rate' id='rating-opt3' data-idx='2' disabled checked={st === 3 ? true : false} />
                                                                        <label for='rating-opt3' ><span>Neutral</span></label>

                                                                        <input type='radio' hidden name='rate' id='rating-opt2' data-idx='3' disabled checked={st === 2 ? true : false} />
                                                                        <label for='rating-opt2' ><span>Dissatisfied</span></label>

                                                                        <input type='radio' hidden name='rate' id='rating-opt1' data-idx='4' disabled checked={st === 1 ? true : false} />
                                                                        <label for='rating-opt1' ><span>Very Dissatisfied</span></label>
                                                                    </div>
                                                                </form>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="comment d-flex justify-content-between">
                                                    <div className=""><span className=""><b>Comment:</b></span></div>
                                                </div>
                                                <div><p>{cmt}</p></div>

                                            </div>
                                        </div>


                                    </div>
                                </div>
                            </div>
                        </div>

                        {/*--------------------------------------------------*/}
                    </div>

                </div>

            </div>


        </>
    );
};
export default CommentBox;