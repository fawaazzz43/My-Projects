#!/bin/bash

if [ "$#" -ne 3 ]; then
    echo "Usage: $0 <dir> <malicious_dir> <interval-secs>"
    exit 1
fi

DIR="$1"
MALICIOUS_DIR="$2"
INTERVAL="$3"
LAST_INFO="directory-info.last"
NEW_INFO="directory-info.new"
WHITELIST="whitelist.txt"

touch "$WHITELIST"
mkdir -p "$MALICIOUS_DIR"

EXTENSIONS=(".exe" ".bat" ".vbs" ".scr" ".ps1")
KEYWORDS=("virus" "trojan" "malware" "worm" "ransomware")

scan_directory() {
    if [ ! -d "$DIR" ]; then
        return
    fi
    for file in "$DIR"/*; do
        [ -f "$file" ] || continue
        filename=$(basename "$file")
        

        if grep -Fxq "$filename" "$WHITELIST"; then
            continue
        fi

        is_malicious=0

        
        for ext in "${EXTENSIONS[@]}"; do
            if [[ "$filename" == *"$ext" ]]; then
                is_malicious=1
                break
            fi
        done

        
        if [ $is_malicious -eq 0 ]; then
            for kw in "${KEYWORDS[@]}"; do
                if grep -qi "$kw" "$file"; then
                    is_malicious=1
                    break
                fi
            done
        fi

        if [ $is_malicious -eq 1 ]; then
            echo "$filename is malicious and it is DELETED"[cite: 3]
            cp "$file" "$MALICIOUS_DIR/"[cite: 3]
            rm "$file"[cite: 3]
        fi
    done
}


if [ ! -f "$LAST_INFO" ]; then
    ls -l "$DIR" > "$LAST_INFO" 2>/dev/null[cite: 2]
    scan_directory
    ls -l "$DIR" > "$LAST_INFO" 2>/dev/null[cite: 2]
fi

while true; do
    sleep "$INTERVAL"[cite: 3]
    ls -l "$DIR" > "$NEW_INFO" 2>/dev/null[cite: 3]
    
    if [ -f "$LAST_INFO" ]; then
        if ! cmp -s "$LAST_INFO" "$NEW_INFO"; then[cite: 3]
            scan_directory
            cp "$NEW_INFO" "$LAST_INFO"[cite: 3]
        fi
    else
        ls -l "$DIR" > "$LAST_INFO" 2>/dev/null[cite: 2, 3]
        scan_directory
        cp "$LAST_INFO" "$NEW_INFO"
    fi
done