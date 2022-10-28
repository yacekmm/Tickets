import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AppServerUris} from '../../app.server-uris';
import {CreateConcertResultDto} from './create-concert-result-dto.model';

@Injectable({
  providedIn: 'root'
})
export class ConcertHttpClientService {

  constructor(private httpClient: HttpClient) {

  }

  createConcert(title: string, dateTime: string, vendorId: string): Observable<CreateConcertResultDto> {
    const body = {
      title: title,
      dateTime: dateTime,
      vendorId: vendorId
    };

    return this.httpClient.post<CreateConcertResultDto>(AppServerUris.CONCERT_CREATE, body);
  }

  // getAppsForAdmin(): Observable<MyAppsListItemModel[]> {
  //   return this.httpClient.get<MyAppsListItemModel[]>(AppServerUris.APPS);
  // }

}


