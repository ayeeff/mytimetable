# MyTimetable - GitHub Setup Guide

## Quick Start

### 1. Create GitHub Repository
1. Go to https://github.com/new
2. Name your repository (e.g., `mytimetable-assignment`)
3. Choose "Public" or "Private"
4. Click "Create repository"

### 2. Upload Your Files
Option A - Web Upload:
1. In your new repo, click "Add file" → "Upload files"
2. Drag and drop all files from this folder:
   - `src/` folder (all .java files)
   - `courses.csv`
   - `.github/workflows/java-ci.yml`
3. Click "Commit changes"

Option B - Command Line:
```bash
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git
git push -u origin main
```

### 3. Run GitHub Actions
1. Go to your repository on GitHub
2. Click "Actions" tab
3. You should see "Java CI" workflow
4. Click on it, then click "Run workflow" → "Run workflow"
5. Wait for the build to complete (about 1-2 minutes)

### 4. View Results
- **Green checkmark**: Code compiled and ran successfully
- **Red X**: Check the logs for compilation errors
- Click on the workflow run to see detailed logs

## What GitHub Actions Does

Every time you push code, GitHub will:
1. ✅ Compile all Java files (`javac src/*.java`)
2. ✅ Run the application with test inputs
3. ✅ Verify search functionality works
4. ✅ Upload compiled .class files as artifacts

## Manual Testing Online

You can also use GitHub Codespaces (free):
1. Click "Code" → "Codespaces" → "Create codespace on main"
2. Wait for the environment to load
3. In the terminal, run:
   ```bash
   javac src/*.java -d bin
   java -cp bin MyTimetable
   ```

## Troubleshooting

**Compilation errors?**
- Check that all files are in `src/` folder
- Verify `courses.csv` is in root directory
- Check GitHub Actions logs for specific errors

**Runtime errors?**
- Ensure `courses.csv` has correct format
- Check that CSV file is in root directory
- Verify Java 8 compatibility

## Assignment Submission

After verifying it works on GitHub:
1. Download the zip file from this repo
2. Submit to Canvas as required
3. Include link to GitHub repo in comments (optional)
