import { Component, OnInit } from '@angular/core';
import { Profiled } from 'src/app/models/dprofile';
import { Full_Display } from 'src/app/models/fulldisplay';

@Component({
  selector: 'app-displayblock',
  templateUrl: './displayblock.component.html',
  styleUrls: ['./displayblock.component.css']
})
export class DisplayblockComponent implements OnInit {
  
  profile1 : Profiled = {
    profileId: 1,
    username: "one",
    image: "../img/g.jpg",
    firstname: "hello",
    lastname: "there"
  };

  photo: any = "../img/p.jpg";

  display1 : Full_Display = {
    displayId: 1,
    image: this.photo,
    profile: this.profile1,
    likers: 7,
    description:"hello"

  }

  displays : Array<Full_Display> = [this.display1];
  constructor() { }

  ngOnInit(): void {
  }

  liked(){

  }

}
