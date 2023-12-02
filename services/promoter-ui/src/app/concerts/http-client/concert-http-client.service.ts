import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AppServerUris} from '../../app.server-uris';
import {CreateConcertResultDto} from './create-concert-result-dto.model';
import {ConcertDto} from "./concert-dto.model";

@Injectable({
  providedIn: 'root'
})
export class ConcertHttpClientService {

  PROMOTER_ID = 'promoter-id';

  constructor(private httpClient: HttpClient) {

  }

  createConcert(title: string, date: string): Observable<CreateConcertResultDto> {
    const body = {
      title: title,
      date: date,
      promoterId: this.PROMOTER_ID
    };

    return this.httpClient.post<CreateConcertResultDto>(AppServerUris.CONCERT_CREATE, body);
  }

  getConcerts(): Observable<ConcertDto[]> {
    return this.httpClient.get<ConcertDto[]>(AppServerUris.CONCERT_GET_ALL + "/" + this.PROMOTER_ID);
  }

}


