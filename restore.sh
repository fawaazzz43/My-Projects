#!/bin/bash

if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <dir> <malicious_dir>"[cite: 3]
    exit 1
fi

DIR="$1"
MALICIOUS_DIR="$2"
WHITELIST="whitelist.txt"
touch "$WHITELIST"

if [ ! -d "$MALICIOUS_DIR" ] || [ -z "$(ls -A "$MALICIOUS_DIR" 2>/dev/null)" ]; then
    echo "No malicious files to review."
    exit 0
fi

while true; do
    files=("$MALICIOUS_DIR"/*)
    if [ ${#files[@]} -eq 0 ] || [ ! -e "${files[0]}" ]; then
        echo "No malicious files to review."
        break
    fi

    echo "Current files in quarantine:"
    i=1
    declare -A file_map
    for f in "${files[@]}"; do
        [ -f "$f" ] || continue
        fname=$(basename "$f")
        echo "$i) $fname"[cite: 3]
        file_map[$i]="$f"
        i=$((i + 1))
    done

    if [ ${#file_map[@]} -eq 0 ]; then
        echo "No malicious files to review."
        break
    fi

    read -p "Pick a file by number (or 0 to exit): " choice
    if [ "$choice" = "0" ]; then
        break
    fi

    selected_file="${file_map[$choice]}"
    if [ -z "$selected_file" ]; then
        echo "Invalid choice."
        continue
    fi

    fname=$(basename "$selected_file")

    echo "1) Restore this file back into $DIR (it was a false positive)"
    echo "2) Permanently delete this file from $MALICIOUS_DIR (it was genuinely malicious)"
    echo "3) Leave this file as-is and go back to the list"[cite: 4]
    read -p "Choose an option (1-3): " option

    case "$option" in
        1)
            cp "$selected_file" "$DIR/"
            rm "$selected_file"
            echo "Restored $fname to $DIR."[cite: 4]
            if ! grep -Fxq "$fname" "$WHITELIST"; then
                echo "$fname" >> "$WHITELIST"
            fi
            ;;
        2)
            rm "$selected_file"
            echo "$fname permanently deleted."[cite: 4]
            ;;
        3)
            continue
            ;;
        *)
            echo "Invalid option."
            ;;
    esac
done