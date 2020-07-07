/*
服务端主程序
by:wangber
*/
package main

import (
	"fmt"
	"net/http"
)

func seeUrlHand(w http.ResponseWriter,r *http.Request){
	fmt.Println(r.FormValue("imageContent"))
	re := MainLogic(r.FormValue("imageContent"))
	w.Write([]byte(re))

}
func mainServer()(re string){
	server := http.Server{
		Addr:              "127.0.0.1:20002",
	}
	http.HandleFunc("/", seeUrlHand)
	server.ListenAndServe()

	return re
}
func main(){
	fmt.Println("服务端已启动......")
	mainServer()
}
