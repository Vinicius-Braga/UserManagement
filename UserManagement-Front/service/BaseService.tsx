import axios from 'axios';

export const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080'
});

export class BaseService {

      url : string;

      constructor(url : string) {
            this.url = url;
      }

      findByID(id: number) {
            return axiosInstance.get(this.url + "/" + id)
      }

      listAll() {
            return axiosInstance.get(this.url);
      }

      create(objeto : any) {
            return axiosInstance.post(this.url, objeto);
      }

      alter(objeto : any) {
            return axiosInstance.put(this.url, objeto);
      }

      delete(id : number) {
            return axiosInstance.delete(this.url + "/" + id)
      }


}
