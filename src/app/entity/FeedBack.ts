// FeedBack.ts
import {Booking} from "./Booking";
import {UserModel} from "./User.model";

export class FeedBack {
  feedId!: number;
  feed_Back!: string;
  rate!: number;
  booking!: Booking; // Assuming you have a Booking class
  user!: UserModel;
}
