package main

import (
	"fmt"
	"os"
	"os/exec"
)

func main() {
	classname := `isdev`
	pgdir := `C:\oit\isdev24-byod\PortableGit-2.42.0-64`
	os.Setenv("CHERE_INVOKING", "1")
	os.Setenv("MSYSTEM", "MINGW64")
	uprofile := os.Getenv("USERPROFILE")
	workhome := uprofile + `\oithomes\` + classname
        os.Chdir(workhome)
	cmd := exec.Command(pgdir + `\usr\bin\mintty.exe`,`-i`,pgdir + `\` + classname + `-bash.ico`, pgdir + `\usr\bin\bash.exe`, `--login`, `-i`)
	cmd.Stdin = os.Stdin
	cmd.Stdout = os.Stdout
	cmd.Stderr = os.Stderr
	if err := cmd.Run(); err != nil {
		fmt.Fprintln(os.Stderr, err)
		os.Exit(1)
	}
        fmt.Printf("exec command")
}