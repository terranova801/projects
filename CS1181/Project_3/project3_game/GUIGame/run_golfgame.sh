#!/usr/bin/env bash
# Run the Golf GUI game

# Go to the folder this script lives in (so it works on any machine/path)
SCRIPT_DIR="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" &>/dev/null && pwd)"
cd "$SCRIPT_DIR" || exit 1

# Compile everything in src into bin
javac -d bin src/*.java || {
    echo "Compilation failed."
    exit 1
}

# Run main class
java -cp bin App
