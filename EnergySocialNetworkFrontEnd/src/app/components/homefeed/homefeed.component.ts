import { Component, OnInit } from '@angular/core';
import { display } from 'src/app/models/display';
import { DisplayServiceService } from 'src/app/service/display-service.service';

@Component({
  selector: 'app-homefeed',
  templateUrl: './homefeed.component.html',
  styleUrls: ['./homefeed.component.css']
})
export class HomefeedComponent implements OnInit {

  post_desciption:string=""
  user:display={username:"matt",desciption:"hello",img:"",like:false};
  user2:display={username:"kev",desciption:"bye",img:"",like:true};
  //displays:Array<display> = [ this.user,this.user2 ];
  displays:Array<any> = [];
  isLiked:boolean = false;
  isVisable:boolean = false;
  constructor(private dispaySer : DisplayServiceService) {}

  ngOnInit(): void {
    this.dispaySer.getAllDisplays().subscribe(responseBody =>{
      this.displays = responseBody.data;
    })
  }

  goToUser(e:any){
    console.log(e.target.innerText);
  }

  goToOtherUser(e:any){
    console.log(e.target.innerText);
  }

  toggleLike(e:any){
    e.target.like = !e.target.like;
    if(e.target.like == true){
      e.target.innerText = "Dislike";
    }else{
      e.target.innerText = "Like";
    }
    console.log(e.target.like);
    //this.isLiked=!this.isLiked;
  }

  togglePost(){
    this.isVisable = !this.isVisable;
  }

  /* 
  sends a post request
  brings you back to the homefeed
  */
  post(){
    console.log(this.post_desciption);
  }

}
