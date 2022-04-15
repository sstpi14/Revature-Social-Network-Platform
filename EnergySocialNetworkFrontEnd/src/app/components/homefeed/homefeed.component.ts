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
  user:display={username:"matt",desciption:"hello"};
  user2:display={username:"kev",desciption:"bye"};
  displays:Array<display> = [this.user,this.user2];
  isLiked:boolean = false;
  isVisable:boolean = false;
  constructor(private dispaySer : DisplayServiceService) {}

  ngOnInit(): void {
    //this.getAllDisplays
  }

  getAllDisplays(){

  }

  goToUser(e:any){
    console.log(e.target.innerText);
  }

  goToOtherUser(e:any){
    console.log(e.target.innerText);
  }

  toggleLike(){
    this.isLiked=!this.isLiked;
  }

  togglePost(){
    this.isVisable = !this.isVisable;
  }

  post(){
    console.log(this.post_desciption);
  }

}
