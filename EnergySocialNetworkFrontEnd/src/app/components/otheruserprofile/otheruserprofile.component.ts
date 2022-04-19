import { Component, OnInit } from '@angular/core';
import { Profiled } from 'src/app/models/dprofile';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-otheruserprofile',
  templateUrl: './otheruserprofile.component.html',
  styleUrls: ['./otheruserprofile.component.css']
})
export class OtheruserprofileComponent implements OnInit {

  profile1 : Profiled = {
    profileId: 1,
    username: "one",
    image: "../img/g.jpg",
    firstname: "hello",
    lastname: "there"
  };

  profileId : any = null;

  profiles : Array<Profiled> = [];

  constructor(private profileApi : ApiService) { }

  ngOnInit(): void {
  }

  getProfileOfOtherUser(){
      this.profileApi.getOneProfileByProfileId(this.profileId).subscribe(responseBody => {

      });
  }
}
