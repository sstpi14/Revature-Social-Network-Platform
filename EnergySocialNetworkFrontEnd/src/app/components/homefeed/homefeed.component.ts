import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { display } from 'src/app/models/display';
import { Profiled } from 'src/app/models/dprofile';
import { Profile } from 'src/app/models/profile';
import { DisplayServiceService } from 'src/app/service/display-service.service';
import { ApiService } from 'src/app/services/api.service';

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
  id!: number;

  profile : Profiled = {
    profileId: 0,
    username: '',
    firstname: '',
    lastname: '',
    image: ''
  }
  constructor(private dispaySer : DisplayServiceService, private route : ActivatedRoute, private apiServ : ApiService) {}

  ngOnInit(): void {
    //this.getAllDisplays
    this.route.queryParams
    .subscribe(params => {
      this.id = params['id'];
    })
    this.getOne();
  }

  getOne(){
    this.apiServ.getOneProfileByProfileId(this.id).subscribe(response => {
      this.profile = response.data;
      console.log(this.profile);
    })
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
